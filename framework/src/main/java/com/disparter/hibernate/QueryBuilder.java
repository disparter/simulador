package com.disparter.hibernate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.disparter.model.AuditableEntity;
import com.disparter.model.BaseEntity;

/**
 * Auxilia na formacao de consultas hql
 * 
 * @author saulo.brito
 *
 */
public class QueryBuilder {

    public enum ConditionType {
        AND, OR
    }

    private String resultList;
    private StringBuilder queryBase;
    private StringBuilder sbConditions;
    private Map<String, Object> mapConditions;
    private List<OrderItem> ordersList;
    private StringBuilder sbNestedCondition;
    private Map<String, Object> sideParameters;
    
    private Long maxPriorityUsed = 0L;
    private ConditionType currentNestedType;

    private QueryBuilder(String queryBase) {
        this.queryBase = new StringBuilder(queryBase);
        this.sbConditions = new StringBuilder();
        this.mapConditions = new HashMap<>();
        this.ordersList = new ArrayList<>();
        this.sideParameters = new HashMap<>();
    }

    @SuppressWarnings({"rawtypes"})
    public static List<Object> getIds(List<BaseEntity> itens) {
        List<Object> ids = new ArrayList<>();
        if (itens == null) {
            return ids;
        }
        for (BaseEntity v : (List<BaseEntity>) itens) {
            if(v != null){
                ids.add(v.getId());
            }
        }
        return ids;
    }

    public static QueryBuilder create(String queryBase) {
        return new QueryBuilder(queryBase);
    }
    
    public QueryBuilder append(String queryBase){
        this.queryBase.append(" " + queryBase);
        return this;
    }

    public QueryBuilder withResultList(String resultList){
        this.resultList = resultList;
        return this;
    }
    
    public QueryBuilder appendToResultList(String resultList){
        this.resultList += resultList;
        return this;
    }
    
    public QueryBuilder setParameter(String parameterName, Object parameterValue){
        this.sideParameters.put(parameterName, parameterValue);
        return this;
    }
    
    /* CONDITIONS */
    
    public QueryBuilder initNestle(ConditionType tipo){
        this.currentNestedType = tipo;
        this.sbNestedCondition = new StringBuilder();
        return this;
    }
    
    public QueryBuilder endNestle(){
        if(this.sbNestedCondition != null){
            String nestleStr = this.sbNestedCondition.insert(0, " ( ").append(" ) ").toString();
            this.sbNestedCondition = null;
            appendQuery(nestleStr, this.currentNestedType);
            this.currentNestedType = null;
        }
        return this;
    }
    
    public QueryBuilder addCondition(Object source, String propertyName, ConditionType tipo) {
        if (source != null) {
            String parameterName = defineParameterName(propertyName);
            appendQuery(" " + definePropertyName(propertyName) + " = :" + parameterName, tipo);
            mapConditions.put(parameterName, source);
        }
        return this;
    }
    
    public QueryBuilder addCondition(BaseEntity<?> source, String propertyName, ConditionType tipo) {
        if (source != null && source.getId() != null) {
            String parameterName = defineParameterName(propertyName);
            appendQuery(" " + definePropertyName(propertyName) + " = :" + parameterName, tipo);
            mapConditions.put(parameterName, source);
        }
        return this;
    }
    
    public QueryBuilder addCondition(String source, String propertyName, ConditionType tipo) {
        if (StringUtils.isNotEmpty(source)) {
            String parameterName = defineParameterName(propertyName);
            appendQuery(" " + definePropertyName(propertyName) + " = :" + parameterName, tipo);
            mapConditions.put(parameterName, source);
        }
        return this;
    }
    
    public QueryBuilder addLikeCondition(String source, String propertyName, ConditionType tipo) {
        if (StringUtils.isNotEmpty(source)) {
            String parameterName = defineParameterName(propertyName);
            appendQuery(" UPPER(" + definePropertyName(propertyName) + ") LIKE UPPER(:" + parameterName+ ")", tipo);
            mapConditions.put(parameterName, source);
        }
        return this;
    }
    
    public QueryBuilder validateActiveEntitys(String... propertyName){
        for(String property : propertyName){
            addCondition(Boolean.TRUE, property + "." + BaseEntity.ACTIVE_PROPERTY_NAME, ConditionType.AND);
        }
        return this;
    }
    
    public QueryBuilder validateDeletedEntitys(String... propertyName){
        for(String property : propertyName){
            addCondition(Boolean.FALSE, property + "." + AuditableEntity.DELETED_PROPERTY_NAME, ConditionType.AND);
        }
        return this;
    }

    @SuppressWarnings("rawtypes")
    public QueryBuilder addCondition(List source, String propertyName, ConditionType tipo) {
        if (source != null && !source.isEmpty()) {
            String parameterName = defineParameterName(propertyName);
            appendQuery(" " + definePropertyName(propertyName) + " IN (:" + parameterName + ")", tipo);
            mapConditions.put(parameterName, source);
        }
        return this;
    }

    public QueryBuilder addCondition(Long source, String propertyName, ConditionType tipo) {
        if (source != null) {
            String parameterName = defineParameterName(propertyName);
            appendQuery(" " + definePropertyName(propertyName) + " = :" + parameterName, tipo);
            mapConditions.put(parameterName, source);
        }
        return this;
    }

    public QueryBuilder addBetweenCondition(Object sourceFrom, Object sourceTo, String propertyName, ConditionType tipo) {
        if (sourceFrom != null && sourceTo != null) {
            String parameterNameFrom = defineParameterName(propertyName).concat("From");
            String parameterNameTo = defineParameterName(propertyName).concat("To");
            appendQuery(" " + definePropertyName(propertyName) + " BETWEEN :" + parameterNameFrom + " AND :"
                    + parameterNameTo, tipo);
            mapConditions.put(parameterNameFrom, sourceFrom);
            mapConditions.put(parameterNameTo, sourceTo);
        }
        return this;
    }

    public QueryBuilder addGreaterCondition(Object source, String propertyName, ConditionType tipo) {
        if (source != null) {
            String parameterName = defineParameterName(propertyName);
            appendQuery(" " + definePropertyName(propertyName) + " > :" + parameterName, tipo);
            mapConditions.put(parameterName, source);
        }
        return this;
    }

    public QueryBuilder addLessCondition(Object source, String propertyName, ConditionType tipo) {
        if (source != null) {
            String parameterName = defineParameterName(propertyName);
            appendQuery(" " + definePropertyName(propertyName) + " < :" + parameterName, tipo);
            mapConditions.put(parameterName, source);
        }
        return this;
    }

    public QueryBuilder addNotEqualsCondition(Object source, String propertyName, ConditionType tipo) {
        if (source != null) {
            String parameterName = defineParameterName(propertyName);
            appendQuery(" " + definePropertyName(propertyName) + " != :" + parameterName, tipo);
            mapConditions.put(parameterName, source);
        }
        return this;
    }
    
    public QueryBuilder isNull(String propertyName, ConditionType tipo){
        appendQuery(" " + definePropertyName(propertyName) + " IS NULL ", tipo);
        return this;
    }
    
    public QueryBuilder isNotNull(String propertyName, ConditionType tipo){
        appendQuery(" " + definePropertyName(propertyName) + " IS NOT NULL ", tipo);
        return this;
    }
    
    /* ORDERS */

    public QueryBuilder addOrder(String propertyName) {
        return addOrder(propertyName, SortType.ASC);
    }
    
    public QueryBuilder addOrder(String propertyName, SortType type) {
        ordersList.add(new OrderItem(propertyName, type, getNextOrderPriority()));
        return this;
    }
    
    public QueryBuilder addOrder(String propertyName, SortType type, long priority) {
        ordersList.add(new OrderItem(propertyName, type, priority));
        if(priority > this.maxPriorityUsed){
            this.maxPriorityUsed = priority;
        }
        return this;
    }

    /* ------------------- */

    public <T> TypedQuery<T> build(EntityManager em, Class<T> tipo) {
        TypedQuery<T> query = em.createQuery(buildQueryString(), tipo);
        setQueryParameters(query);
        return query;
    }

    public Query build(EntityManager em) {
        Query query = em.createQuery(buildQueryString());
        setQueryParameters(query);
        return query;
    }
    
    public TypedQuery<Long> buildCountQuery(EntityManager em){
        TypedQuery<Long> query = em.createQuery(buildCountQueryString(), Long.class);
        setQueryParameters(query);
        return query;
    }

    private void setQueryParameters(Query query) {
        for (Map.Entry<String, Object> entry : this.mapConditions.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Object> entry : this.sideParameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }

    private String buildQueryString() {
        if(this.sbNestedCondition != null){
            endNestle();
        }
        StringBuilder sb = getQueryWithResultList();
        appendWhereClause(sb);
        appendOrderClause(sb);
        return sb.toString();
    }
    
    private String buildCountQueryString(){
        if(this.sbNestedCondition != null){
            endNestle();
        }
        StringBuilder sb = getQueryWithResultList(" SELECT count(*) ");
        appendWhereClause(sb);
        return sb.toString();
    }

    private void appendQuery(String str, ConditionType type) {
        StringBuilder currentSB = sbNestedCondition != null ? sbNestedCondition : sbConditions;
        if (currentSB.length() > 0) {
            currentSB.append(" " + type.name());
        }
        currentSB.append(" " + str);
    }

    private StringBuilder getQueryWithResultList(){
        if(this.resultList != null){
            return new StringBuilder("SELECT ").append(this.resultList)
                    .append(" ").append(this.queryBase);
        } else {
            return new StringBuilder(this.queryBase);
        }
    }
    
    private StringBuilder getQueryWithResultList(String str){
        return new StringBuilder(str).append(" ").append(this.queryBase);
    }
    
    private void appendWhereClause(StringBuilder sb){
        if (this.sbConditions.length() > 0) {
            sb.append(" WHERE ").append(this.sbConditions);
        }
    }
    
    private void appendOrderClause(StringBuilder sb){
        if(!this.ordersList.isEmpty()){
            Collections.sort(this.ordersList);
            StringBuilder osb = new StringBuilder();
            for(OrderItem oi : this.ordersList){
                if(osb.length() > 0){
                    osb.append(", ");
                }
                osb.append(oi);
            }
            sb.append(" ORDER BY ").append(osb);
        }
    }
    
    private String defineParameterName(String str) {
        String name;
        if (str.lastIndexOf("->") > 0) {
            name = str.split("->")[1];
        } else {
            name = str;
        }
        return name.replace(".", "").trim();
    }

    private String definePropertyName(String str) {
        if (str.lastIndexOf("->") > 0) {
            return str.split("->")[0].trim();
        } else {
            return str.trim();
        }
    }
    
    private long getNextOrderPriority(){
        return ++this.maxPriorityUsed;
    }
    
    /* ---------------- */
    
    private class OrderItem implements Comparable<OrderItem>{
        String propertyName;
        SortType type;
        Long priority;
        
        OrderItem(String propertyName, SortType type, Long priority){
            this.propertyName = propertyName;
            this.type = type;
            this.priority = priority;
        }

        @Override
        public int compareTo(OrderItem o) {
            return Long.compare(this.priority, o.priority);
        }
        
        @Override
        public int hashCode() {
            return new HashCodeBuilder().append(type).build();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;

            OrderItem other = (OrderItem) obj;
            return new EqualsBuilder().append(other.propertyName, propertyName).build();
        }
        
        @Override
        public String toString(){
            return propertyName.concat(" " + type.name());
        }
        
    }
    
}