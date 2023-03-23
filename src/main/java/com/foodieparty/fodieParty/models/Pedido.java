package com.foodieparty.fodieParty.models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,  generator = "native")
    @GenericGenerator(name="native", strategy="native")
    private long id;
}
