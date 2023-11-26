package com.project.content.contentmanagementservice.dao.impl;

import com.google.firebase.database.DatabaseReference;
import com.project.content.contentmanagementservice.dao.FireBaseTemplateDAO;
import com.project.content.contentmanagementservice.model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FireBaseTemplateDAOImpl implements FireBaseTemplateDAO {

    @Autowired
    DatabaseReference databaseReference;

    @Override
    public void saveFireBaseTemplate(Template template) {
        databaseReference.child("TEMPLATE_STORE").child(template.getId()).setValueAsync(template);
    }
}
