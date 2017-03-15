package mx.com.technogi.testing.services;


import mx.com.technogi.testing.domain.Invoice;
import mx.com.technogi.testing.domain.Subscription;
import mx.com.technogi.testing.gateways.Account;
import mx.com.technogi.testing.gateways.AccountGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    private final AccountGateway accountGateway;

    @Autowired
    public SubscriptionService(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }


    public Invoice createInvoice(Subscription subscription) {
        Account account = accountGateway.getById(subscription.getAccountId());
        if(account.getType().equals("friends")) {
            return new Invoice(account.getEmail(), 0);
        }

        return new Invoice(account.getEmail(), subscription.getSubscriptionType().getFeeInPenny());
    }
}
