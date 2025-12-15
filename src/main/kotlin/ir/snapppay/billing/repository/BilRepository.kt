package ir.snapppay.billing.repository

import ir.bamap.blu.jpa.repository.BluRepositoryImpl
import ir.snapppay.billing.entity.BillComponent
import ir.snapppay.billing.entity.Participant
import org.springframework.stereotype.Repository

@Repository
class BilRepository: BluRepositoryImpl<BillComponent, Long>(BillComponent::class.java) {

}