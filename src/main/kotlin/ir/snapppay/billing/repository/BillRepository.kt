package ir.snapppay.billing.repository

import ir.bamap.blu.jpa.repository.BluRepositoryImpl
import ir.snapppay.billing.entity.BillComponent
import org.springframework.stereotype.Repository

@Repository
class BillRepository: BluRepositoryImpl<BillComponent, Long>(BillComponent::class.java) {

}