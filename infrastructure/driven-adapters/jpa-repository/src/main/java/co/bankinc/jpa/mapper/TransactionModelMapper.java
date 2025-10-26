package co.bankinc.jpa.mapper;

import co.bankinc.jpa.entity.TransactionEntity;
import co.bankinc.model.card.model.TransactionModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductTransactionModelMapper.class})
@Component
public interface TransactionModelMapper {


    TransactionModel toModel(TransactionEntity transactionEntity);


    @Mapping(target = "productsTransaction" , ignore = true)
    TransactionEntity toEntity(TransactionModel transactionModel);


}
