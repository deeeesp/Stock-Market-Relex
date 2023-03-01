package ru.stazaev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.stazaev.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet,Long> {

    @Query("select w from Wallet w where w.secret_key = :key")
    Wallet findBySecretKey(@Param("key") String key);

    @Modifying
    @Transactional
    @Query("update Wallet set RUB_wallet = RUB_wallet + :rub where secret_key = :key")
    void updateRubBalance(@Param("key") String key, @Param("rub") double rub);

    @Modifying
    @Transactional
    @Query("update Wallet set BTC_wallet = BTC_wallet +:btc where secret_key = :key")
    void updateBtcBalance(@Param("key") String key, @Param("btc") double btc);

    @Modifying
    @Transactional
    @Query("update Wallet set TON_wallet = TON_wallet + :ton where secret_key = :key")
    void updateTonBalance(@Param("key") String key, @Param("ton") double ton);

    @Query("select sum (RUB_wallet) from Wallet ")
    double countAllByRub();

    @Query("select sum (BTC_wallet) from Wallet ")
    double countAllByBtc();

    @Query("select sum (TON_wallet) from Wallet ")
    double countAllByTon();

}
