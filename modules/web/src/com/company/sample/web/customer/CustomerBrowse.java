package com.company.sample.web.customer;

import com.company.sample.entity.Customer;
import com.haulmont.chile.core.model.MetaPropertyPath;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.AggregationInfo;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.data.GroupDatasource;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

public class CustomerBrowse extends AbstractLookup {
    @Inject
    private GroupTable<Customer> customersTable;
    @Inject
    private GroupDatasource<Customer, UUID> customersDs;

    @Override
    public void init(Map<String, Object> params) {
        MetaPropertyPath metaPropertyPath = customersDs.getMetaClass().getPropertyPath("grade");

        AggregationInfo info = new AggregationInfo();
        info.setPropertyPath(metaPropertyPath);
        info.setStrategy(new CustomerGradeAggregation());

        customersTable.getColumn("grade")
                .setAggregation(info);
    }
}