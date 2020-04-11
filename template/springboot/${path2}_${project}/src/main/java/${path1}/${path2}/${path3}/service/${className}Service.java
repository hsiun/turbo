package ${projectPackage}.service;

import com.imooc.dataobject.${className};

import java.util.List;


public interface ${className}Service {

    ${className} findOne(Integer id);

    List<${className}> findAll();

    List<${className}> findBy${className}TypeIn(List<Integer> typeList);

    ${className} save(${className} ${className?uncap_first});
}
