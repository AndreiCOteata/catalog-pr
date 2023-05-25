package org.unibuc.persistance.converter.impl;

import org.unibuc.persistance.converter.CardConverter;
import org.unibuc.persistance.dto.CardDto;
import org.unibuc.persistance.model.Card;
import org.unibuc.persistance.model.impl.CardImpl;

public class CardConverterImpl implements CardConverter {
    @Override
    public Card convertFromDto(CardDto cardDto) {
        return CardImpl.builder()
                .id(cardDto.getId())
                .accountId(cardDto.getAccountId())
                .status(cardDto.getStatus())
                .cvv(cardDto.getCvv())
                .number(cardDto.getNumber())
                .expiryDate(cardDto.getExpiryDate())
                .build();
    }

    @Override
    public CardDto convertFromEntity(Card card) {
        return CardDto.builder()
                .id(card.getId())
                .accountId(card.getAccountId())
                .status(card.getStatus())
                .cvv(card.getCvv())
                .number(card.getNumber())
                .expiryDate(card.getExpiryDate())
                .build();
    }
}
