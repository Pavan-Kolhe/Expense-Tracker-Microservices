//package com.expense.expenseService.consumer;
//
//import com.expense.expenseService.dto.ExpenseDto;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.kafka.common.serialization.Deserializer;
//
//import java.util.Map;
//
//public class ExpenseDeserializer implements Deserializer<ExpenseDto>
//{
//
//    @Override public void close() {
//    }
//    @Override public void configure(Map<String, ?> arg0, boolean arg1) {
//    }
//
//    @Override
//    public ExpenseDto deserialize(String arg0, byte[] arg1) {
//        ObjectMapper mapper = new ObjectMapper();
//        ExpenseDto expense = null;
//        try {
//            expense = mapper.readValue(arg1, ExpenseDto.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return expense;
//    }
//
//
//
// }

package com.expense.expenseService.consumer;

import com.expense.expenseService.dto.ExpenseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class ExpenseDeserializer implements Deserializer<ExpenseDto> {

    // 1. Create the translator ONCE
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}

    @Override
    public ExpenseDto deserialize(String topic, byte[] data) {
        // 2. Protect against empty messages
        if (data == null) {
            return null;
        }

        try {
            return mapper.readValue(data, ExpenseDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {}
}