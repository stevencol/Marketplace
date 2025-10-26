package co.bankinc.jpa;

import co.bankinc.jpa.entity.ProductTransactionEntity;
import co.bankinc.jpa.entity.TransactionEntity;
import co.bankinc.jpa.mapper.TransactionModelMapper;
import co.bankinc.model.card.gateways.out.ITransactionRepository;
import co.bankinc.model.card.model.ProductTransactionModel;
import co.bankinc.model.card.model.TransactionModel;
import constants.MessageError;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static constants.MessageError.ENTIDAD_NO_ENCONTRADA;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component("transactionJpaAdapter")
@Slf4j
public class TransactionJpaAdapter implements ITransactionRepository {

    private final TransactioJPARepository transactionRepository;
    private final TransactionModelMapper transactionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TransactionModel createTransaction(TransactionModel transactionModel) {
        TransactionEntity transactionSave = transactionMapper.toEntity(transactionModel);
        log.info("TransactionEntity to save: {}", transactionSave);
        transactionSave = transactionRepository.save(transactionSave);

        return transactionMapper.toModel(createProducsTransactions(transactionSave, transactionModel));
    }

    @Override
    public TransactionModel findById(Long id) {
        return transactionMapper.toModel(transactionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format(ENTIDAD_NO_ENCONTRADA, id))));
    }

    @Override
    public TransactionModel updateTransaction(TransactionModel transactionModel) {

        return transactionMapper.toModel(transactionRepository.save(transactionMapper.toEntity(transactionModel)));
    }

    @Override
    public List<TransactionModel> getTransactions() {
        return transactionRepository.findAll().stream().map(transactionMapper::toModel).toList();
    }


    private TransactionEntity createProducsTransactions(TransactionEntity savedTransaction, TransactionModel transactionModel) {

        List<ProductTransactionEntity> productEntities = transactionModel.getProductsTransaction()
                .stream()
                .map(p ->
                        ProductTransactionEntity
                                .builder()
                                .productId(p.getProductId())
                                .transaction(savedTransaction)
                                .build()).collect(Collectors.toList());

        savedTransaction.setProductsTransaction(productEntities);
        return transactionRepository.save(savedTransaction);
    }


}
