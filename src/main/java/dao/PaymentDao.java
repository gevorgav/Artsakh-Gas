package dao;

import Models.Payment;

import java.util.List;

public class PaymentDao extends Dao<Payment> {
    @Override
    List<Payment> loadAll() {
        return null;
    }

    @Override
    Payment loadById(Integer id) {
        return null;
    }

    @Override
    boolean insert(Payment payment) {
        return false;
    }

    @Override
    boolean update(Payment payment) {
        return false;
    }

    @Override
    boolean delete(Integer id) {
        return false;
    }
}
