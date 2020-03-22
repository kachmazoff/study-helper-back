package com.kach.studyhelperback.service.implementation;

import com.kach.studyhelperback.model.ArticleType;
import com.kach.studyhelperback.repository.ArticleTypeRepository;
import com.kach.studyhelperback.service.ArticleTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypesServiceImpl implements ArticleTypesService {
    @Autowired
    ArticleTypeRepository articleTypeRepository;

    @Override
    public List<ArticleType> getAllTypes() {
        List<ArticleType> types = new ArrayList<>();
        articleTypeRepository.findAll().forEach(types::add);
        return types;
    }

    @Override
    public ArticleType getType(String typeName) {
        Optional<ArticleType> articleType = articleTypeRepository.findByName(typeName);
        if (articleType.isEmpty()) {
            throw new IllegalArgumentException("This type does not exist");
        }

        return articleType.get();
    }

    @Override
    public void addType(ArticleType type) {
        if (articleTypeRepository.existsByName(type.getName())) {
            throw new IllegalArgumentException("Type with name '" + type.getName() + "' is already exists");
        }
        type.setId(null);
        articleTypeRepository.save(type);
    }

    @Override
    public void deleteType(Long typeId) {
        if (!articleTypeRepository.existsById(typeId)) {
            throw new IllegalArgumentException("This type does not exist");
        }
        articleTypeRepository.deleteById(typeId);
    }

    @Override
    public void updateType(Long typeId, ArticleType updatedType) {
        if (!articleTypeRepository.existsById(typeId)) {
            throw new IllegalArgumentException("This type does not exist");
        }
        if (articleTypeRepository.existsByNameAndIdNot(updatedType.getName(), typeId)) {
            throw new IllegalArgumentException("Type with name '" + updatedType.getName() + "' is already exists");
        }
        updatedType.setId(typeId);
        articleTypeRepository.save(updatedType);
    }
}
