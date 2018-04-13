package com.ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ua.entity.Dialog;
import com.ua.entity.MyMessage;

@Repository
public interface MyMessageRepository extends JpaRepository<MyMessage, Integer>{
	@Query("SELECT u FROM MyMessage u WHERE u.dialog = :dialog")
	List<MyMessage> findByDialog(@Param("dialog") Dialog dialog);

}
