package co.bankinc.jpa.mapper;

import co.bankinc.jpa.entity.ProductTransactionEntity;
import co.bankinc.model.card.model.ProductTransactionModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface ProductTransactionModelMapper {

    ProductTransactionEntity toEntity(ProductTransactionModel model);

    ProductTransactionModel toModel(ProductTransactionEntity entity);

    List<ProductTransactionEntity> toEntity(List<ProductTransactionModel> model);

    List<ProductTransactionModel> toModel(List<ProductTransactionEntity> entity);

}
