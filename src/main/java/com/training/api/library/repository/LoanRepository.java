package com.training.api.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.api.library.entity.Loan;
import com.training.api.library.entity.composite.LoanId;

public interface LoanRepository extends JpaRepository<Loan, LoanId>{

}
