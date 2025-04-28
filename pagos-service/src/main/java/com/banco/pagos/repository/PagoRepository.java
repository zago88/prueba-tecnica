package com.banco.pagos.repository;

import com.banco.pagos.model.Pago;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends MongoRepository<Pago, String> {
    // Aquí podrías definir métodos de consulta personalizados si los necesitas después
}

