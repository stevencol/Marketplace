package co.bankinc.api;

import co.bankinc.api.dto.card.CardDto;
import co.bankinc.api.dto.card.CardRechargeDto;
import co.bankinc.api.dto.response.ApiResponseDto;
import co.bankinc.api.mapper.CardDtoMapper;
import co.bankinc.api.mapper.CardRechargeMapper;
import co.bankinc.model.card.model.CardModel;
import co.bankinc.model.card.model.CardRechargeModel;
import co.bankinc.usecase.usecase.CardUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static co.bankinc.api.helper.ResponseHelper.createResponseOk;
import static co.bankinc.api.helper.ValidFieldHelpers.validateFields;
import static constants.MessageInfo.OPERACION_EXITOSA;
import static constants.MessageInfo.RECARGA_EXITOSA;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/cards", produces = MediaType.APPLICATION_JSON_VALUE)
public class CardRest {


    private final CardUseCase cardUseCase;
    private final CardDtoMapper cardDtoMapper;
    private final CardRechargeMapper cardRechargeMapper;

    @PostMapping()
    public ResponseEntity<ApiResponseDto<Object>> createCard(@Valid @RequestBody CardDto card, BindingResult result) {

        if (result.hasErrors()) {
            return validateFields(result);
        }
        CardModel cardModel = cardDtoMapper.toModel(card);

        cardModel = cardUseCase.createCard(cardModel);
        return createResponseOk(HttpStatus.OK, cardDtoMapper.cardDto(cardModel), OPERACION_EXITOSA);
    }

    @PutMapping("/recharge")
    public ResponseEntity<ApiResponseDto<Object>> rechargeCard(@Valid @RequestBody CardRechargeDto card, BindingResult result) {
        if (result.hasErrors()) {
            return validateFields(result);
        }
        CardRechargeModel cardRechargeModel = cardRechargeMapper.toModel(card);
        cardRechargeModel = cardUseCase.rechargeCard(cardRechargeModel);
        return createResponseOk(HttpStatus.OK, cardRechargeModel, String.format(RECARGA_EXITOSA, cardRechargeModel.getCardNumber(), cardRechargeModel.getAmount()));
    }


}
