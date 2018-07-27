package dao;

import Models.Payment;

import java.util.List;

public class PaymentDao extends Dao<Payment> {
    @Override
    public List<Payment> loadAll() {
        return null;
    }

    @Override
    public Payment loadById(Integer id) {
        return null;
    }

    @Override
    public boolean insert(Payment payment) {
        return false;
    }

    @Override
    public boolean update(Payment payment) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
