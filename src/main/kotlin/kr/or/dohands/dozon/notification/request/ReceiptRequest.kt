package kr.or.dohands.dozon.notification.request

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NonNull

@Data
@AllArgsConstructor
data class ReceiptRequest(
    @NonNull val ids: List<String>
)
