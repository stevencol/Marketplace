package co.bankinc.api.mapper;

import co.bankinc.api.dto.transaction.TransactionDto;
import co.bankinc.model.card.model.ProductTransactionModel;
import co.bankinc.model.card.model.TransactionModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface TransactionDtoMapper {

    @Mapping(source = "products", target = "productsTransaction", qualifiedByName = "longToEntity")
    TransactionModel toModel(TransactionDto transactionDto);

    @Mapping(source = "productsTransaction", target = "products", qualifiedByName = "entityToLong")
    TransactionDto toDto(TransactionModel transactionModel);


    @Named("longToEntity")
    default List<ProductTransactionModel> toModel(List<Long> productIds) {

        return productIds.stream().map(productId ->
                ProductTransactionModel
                        .builder()
                        .productId(productId)
                        .build()
        ).toList();
    }

    @Named("entityToLong")
    default List<Long> toDto(List<ProductTransactionModel> productTransactionModels) {
        return productTransactionModels.stream().map(ProductTransactionModel::getProductId).toList();
    }
}



