package cn.liguohao.ikaros.service;

import cn.liguohao.ikaros.common.Assert;
import cn.liguohao.ikaros.exceptions.RecordNotFoundException;
import cn.liguohao.ikaros.model.dto.OptionItemDTO;
import cn.liguohao.ikaros.model.entity.OptionEntity;
import cn.liguohao.ikaros.repository.OptionRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * @author guohao
 * @date 2022/10/18
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class OptionService {

    private final OptionRepository optionRepository;

    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public OptionEntity findOptionItemByKey(String key) throws RecordNotFoundException {
        Assert.notBlank(key, "'key' must not be blank");
        if (!optionRepository.existsByKeyAndStatus(key, true)) {
            throw new RecordNotFoundException("target key option record not fond, key=" + key);
        }
        return optionRepository.findByKeyAndStatus(key, true);
    }

    public OptionEntity saveOptionItem(OptionItemDTO optionItemDTO) {
        Assert.notNull(optionItemDTO, "'optionItemDTO' must not be null");
        String key = optionItemDTO.getKey();
        Assert.notBlank(key, "'key' must not be blank");

        try {
            OptionEntity existOptionEntity = findOptionItemByKey(key);
            existOptionEntity.setValue(optionItemDTO.getValue());
            return optionRepository.saveAndFlush(existOptionEntity);
        } catch (RecordNotFoundException e) {
            OptionEntity optionEntity = new OptionEntity()
                .setType(optionItemDTO.getType())
                .setKey(key)
                .setValue(optionItemDTO.getValue());
            return optionRepository.saveAndFlush(optionEntity);
        }

    }

    public void deleteOptionItemByKey(String key) throws RecordNotFoundException {
        Assert.notBlank(key, "'key' must not be blank");
        OptionEntity optionEntity = findOptionItemByKey(key);
        optionEntity.setStatus(false);
        optionRepository.saveAndFlush(optionEntity);
    }


}
