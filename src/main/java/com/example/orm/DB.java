package com.example.orm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DB {

//Diese Funktion persistiert ein ihr übergebenes Objekt:
public static void Persist(Object Eingabe){
    StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();

    Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
    SessionFactory factory = meta.getSessionFactoryBuilder().build();
    Session session = factory.openSession();

    session.beginTransaction();
    session.persist(Eingabe);
    session.flush();
    session.close();
}

//Diese Funktion liest das Objekt mit der ihr übergebenen id ein und gibt es als "Student"-Objekt aus:
public static Student CallStudentById(Integer input){
    StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();

    Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
    SessionFactory factory = meta.getSessionFactoryBuilder().build();
    Session session = factory.openSession();


    session.beginTransaction();
    Student student = session.load(Student.class, input.longValue());
    session.flush();
    return student;
}

//Diese Funktion updatet das ihr übergebene Objekt:
public static void EvictAndMerge(Object objekt, Integer id){
    StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();

    Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
    SessionFactory factory = meta.getSessionFactoryBuilder().build();
    Session session = factory.openSession();


    session.beginTransaction();
    session.evict(session.load(Student.class, id.longValue()));
    session.flush();
    session.merge(objekt);
    session.flush();
    session.close();
}
}
