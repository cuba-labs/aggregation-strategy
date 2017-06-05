package com.company.sample.web.customer;

import com.company.sample.entity.CustomerGrade;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.data.aggregation.AggregationStrategy;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;

/**
 * Calculate the most frequent customer grade
 */
public class CustomerGradeAggregation implements AggregationStrategy<CustomerGrade, String> {
    @Override
    public String aggregate(Collection<CustomerGrade> propertyValues) {
        CustomerGrade mostFrequent = null;
        long max = 0;
        if (CollectionUtils.isNotEmpty(propertyValues)) {
            for (CustomerGrade grade : CustomerGrade.values()) {
                long current = propertyValues.stream()
                        .filter(customerGrade -> customerGrade.equals(grade))
                        .count();

                if (current > max) {
                    mostFrequent = grade;
                    max = current;
                }
            }
        }

        if (mostFrequent != null) {
            Messages messages = AppBeans.get(Messages.NAME);
            return String.format("%s: %d/%d", messages.getMessage(mostFrequent), max, propertyValues.size());
        }

        return null;
    }

    @Override
    public Class<String> getResultClass() {
        return String.class;
    }
}
