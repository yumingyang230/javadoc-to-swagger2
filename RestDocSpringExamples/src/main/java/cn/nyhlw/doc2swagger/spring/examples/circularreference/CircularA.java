package cn.nyhlw.doc2swagger.spring.examples.circularreference;

import lombok.Data;

@Data
public class CircularA {
    private String _name;
    private CircularB _circularB;
}
