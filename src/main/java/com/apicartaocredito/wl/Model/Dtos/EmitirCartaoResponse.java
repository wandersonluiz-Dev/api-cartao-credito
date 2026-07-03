package com.apicartaocredito.wl.Model.Dtos;

import com.apicartaocredito.wl.Model.CartaoCreditoEntity;
import com.apicartaocredito.wl.enums.StatusCartao;
import java.math.BigDecimal;
import java.time.LocalDate;

public record EmitirCartaoResponse(Long id,
                                   String nomeTitular,
                                   String numeroCartao,
                                   String cvv,
                                   LocalDate validade,
                                   BigDecimal limiteTotal,
                                   BigDecimal limiteDisponivel,
                                   StatusCartao status
                                    ) {

    public EmitirCartaoResponse(CartaoCreditoEntity cartao) {
        this( cartao.getId(),
              cartao.getNomeTitular(),
              cartao.getNumero(),
                cartao.getCvv(),
                cartao.getValidade(),
                cartao.getLimiteTotal(),
              cartao.getLimiteDisponivel(),
              cartao.getStatusCartao()
        );
    }
}
