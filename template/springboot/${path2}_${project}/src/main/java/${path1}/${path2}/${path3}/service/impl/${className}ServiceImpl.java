package ${projectPackage}.impl;

import ${projectPackage}.dataobject.${className};
import ${projectPackage}.repository.${className}.Repository;
import ${projectPackage}.service.${className}.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ${className}ServiceImpl implements ${className}Service {

    @Autowired
    private ${className}Repository repository;

    @Override
    public ${className} findOne(Integer id) {
		/**
		 * 查不到返回null
		 * .get 抛异常
		 */
		return repository.findById(id).orElse(null);
    }

    @Override
    public List<${className}> findAll() {
        return repository.findAll();
    }

    @Override
    public List<${className}> findBy${className}TypeIn(List<Integer> typeList) {
        return repository.findBy${className}TypeIn(typeList);
    }

    @Override
    public ${className} save(${className} product${className}) {
        return repository.save(product${className});
    }
}
