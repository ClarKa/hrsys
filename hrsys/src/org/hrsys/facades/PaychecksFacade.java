package org.hrsys.facades;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hrsys.dao.BankManager;
import org.hrsys.dao.PaychecksManager;
import org.hrsys.dto.PaychecksDTO;
import org.hrsys.enums.PaymentMethod;

public class PaychecksFacade {
    public PaychecksDTO getPaychecksForEmployee(int employeeId, PaychecksManager paychecksManager) {
        PaychecksDTO paychecksDTO = new PaychecksDTO(paychecksManager.getPaychecksForEmployee(employeeId));
        return paychecksDTO;
    }
    
    public PaychecksDTO updatePaychecksForEmployee(int employeeId, Map<String, String> map, PaychecksManager paychecksManager, BankManager bankManager) {
        PaymentMethod pm = PaymentMethod.byId(map.get("paymentMethod"));
        PaychecksDTO paychecksDTO = new PaychecksDTO();
        if (pm == null) {
            paychecksDTO.setError("Payment method cannot be null.");
            return paychecksDTO;
        }
        
        map.remove("paymentMethod");
        
        if (pm == PaymentMethod.DD) {
            int sum = 0;
            for(String p : map.values()) {
                
                sum += Integer.valueOf(p);
            }
            if (sum != 100) {
                paychecksDTO.setError("Distribution percent must sum to 100.");
                return paychecksDTO;
            }
        }
        
        try {
            paychecksDTO = new PaychecksDTO(paychecksManager.updatePaychecksForEmpoyee(employeeId, pm));
        } catch (Exception e) {
            paychecksDTO.setError(ExceptionUtils.getRootCause(e).getLocalizedMessage());
            return paychecksDTO;
        }
        
        List<String> list = new ArrayList<String>(map.keySet());
        List<Integer> accountIds = new ArrayList<>();
        for(String s : list) {
            accountIds.add(Integer.valueOf(s));
        }
        try {
            bankManager.updateDistributionForEmployee(map, accountIds, employeeId);
        } catch (Exception e) {
              e.printStackTrace();
              if (ExceptionUtils.getRootCause(e) != null) {
                  paychecksDTO.setError(ExceptionUtils.getRootCause(e).getLocalizedMessage());
              } else {
                  paychecksDTO.setError(e.getLocalizedMessage());
              }
              return paychecksDTO;
        }
//        Iterator<Entry<String, String>> it = map.entrySet().iterator();
//        
//        while (it.hasNext()) {
//            Map.Entry<String, String> pair = (Map.Entry<String, String>) it.next();
//            int accountId = Integer.valueOf(pair.getKey());
//            int percent = Integer.valueOf(pair.getValue());
//            
//            BankPK pk = new BankPK();
//            pk.setAccountId(accountId);
//            pk.setEmployeeId(employeeId);
//            
//            try {
//                bankManager.updateDistributionForEmployee(pk, percent);
//                it.remove(); // avoids a ConcurrentModificationException
//            } catch (Exception e) {
//                if (ExceptionUtils.getRootCause(e) != null) {
//                    paychecksDTO.setError(ExceptionUtils.getRootCause(e).getLocalizedMessage());
//                } else {
//                    paychecksDTO.setError(e.getLocalizedMessage());
//                }
//                return paychecksDTO;
//            }
//        }
        
        return paychecksDTO;
    }
}
