package atm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CashManager {
    private List<Denomination> denominations;
    private int totalCash;

    public CashManager(int totalCash) {
        this.totalCash = totalCash;
        this.denominations = new ArrayList<>();
        loadCash();
    }

    private void loadCash() {
        // Assuming initial distribution for 100000
        denominations.add(new Denomination(1000, 50)); // 50 * 1000 = 50000
        denominations.add(new Denomination(500, 80));  // 80 * 500 = 40000
        denominations.add(new Denomination(100, 100)); // 100 * 100 = 10000
    }

    public boolean canDispense(int amount) {
        return amount <= totalCash && calculateDenominations(amount) != null;
    }

    public Map<Integer, Integer> calculateDenominations(int amount) {
        Map<Integer, Integer> result = new HashMap<>();
        int remaining = amount;

        if (amount <= 1000) {
            if (remaining >= 500) {
                result.put(500, 1);
                remaining -= 500;
            }
            if (remaining > 0) {
                int hundreds = remaining / 100;
                result.put(100, hundreds);
                remaining -= hundreds * 100;
            }
        } else if (amount <= 5000) {
            result.put(1000, 1);
            remaining -= 1000;
            if (remaining >= 2000) {
                result.put(100, 20);
                remaining -= 2000;
            }
            if (remaining > 0) {
                int fiveHundreds = remaining / 500;
                result.put(500, fiveHundreds);
                remaining -= fiveHundreds * 500;
            }
        } else {
            result.put(1000, 3);
            remaining -= 3000;
            if (remaining >= 2000) {
                result.put(100, 20);
                remaining -= 2000;
            }
            if (remaining > 0) {
                int fiveHundreds = remaining / 500;
                result.put(500, fiveHundreds);
                remaining -= fiveHundreds * 500;
            }
        }

        return remaining == 0 ? result : null;
    }

    public void dispenseCash(int amount) {
        Map<Integer, Integer> dispensed = calculateDenominations(amount);
        if (dispensed != null) {
            for (Denomination denom : denominations) {
                if (dispensed.containsKey(denom.getValue())) {
                    denom.setCount(denom.getCount() - dispensed.get(denom.getValue()));
                }
            }
            totalCash -= amount;
        }
    }

    public int getTotalCash() { return totalCash; }
}