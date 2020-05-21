package cn.yumy.doc2swagger.spring.examples.circularreference;

import lombok.Data;

@Data
public class CircularB {
    private String _name;
    private CircularA _circularA;
}
