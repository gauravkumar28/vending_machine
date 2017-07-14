package com.vending;

import java.util.Collection;
import java.util.List;

public interface CashCalculator {
	
    Integer sum(Collection<Cash> cash_list);

    Integer remaining(List<Cash> credit, Integer price);

    List<Cash> change(List<Cash> cash, Integer remaining);

}

