/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yisheng.DataSet;

/**
 *
 * @author Thomas
 */
public interface ITableProducer 
{
    String[] requestAttributes();
    String[][] requestInstances();
}
