package com.joh.bhms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.joh.bhms.model.OrderDetail;

public interface OrderDetailDAO extends CrudRepository<OrderDetail, Integer> {

	List<OrderDetail> findAllByOrderOrderTimeBetween(Date from, Date to);

	@Query(value = "SELECT * FROM B_ORDER_DETAILS \n" + "INNER JOIN B_PRODUCTS  USING(I_PRODUCT)\n"
			+ "WHERE QUANTITY-SOLD_AMOUNT>0\n" + "AND (EXPIRATION_DATE IS NULL OR EXPIRATION_DATE>CURDATE())\n"
			+ "AND CODE= ?1 ORDER BY I_ORDER_DETAIL LIMIT 1;", nativeQuery = true)
	OrderDetail findByProductCode(String code);

	@Modifying
	@Query("UPDATE OrderDetail od SET od.soldAmount = od.soldAmount+ 1 WHERE od.id = ?1 ")
	int stockDown(int orderDetailId);

	@Modifying
	@Query("UPDATE OrderDetail od SET od.soldAmount = od.soldAmount-1 WHERE od.id = ?1 ")
	int stockUp(int orderDetailId);

}
