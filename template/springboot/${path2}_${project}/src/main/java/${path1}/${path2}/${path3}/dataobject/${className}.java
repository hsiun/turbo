package ${projectPackage}.dataobject;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;


@Entity
@DynamicUpdate
@Table(name = "${table.tableName}")
public class ${className} {

    <#list table.columns as column>
    <#if column.variableIsKey??>
    @Id
    @GeneratedValue
    </#if>
    private ${column.variableType} ${column.variableName};

    </#list>

    <#list table.columns as column>
    public void set${column.variableName?cap_first}(${column.variableType} ${column.variableName}) {
        this.${column.variableName} = ${column.variableName}; 
    }

    public String get${column.variableName?cap_first}() {
        return this.${column.variableName};
    }
    
    <#/list>


}
