//package kr.or.dohands.dozon.exp.domain
//
//import jakarta.persistence.*
//
//@Entity(name = "exp-history-week")
//class ExpHistoryWeek(
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    val id: Long,
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(nullable = false, name = "number")
//    val expId: Exp,
//
//    val week: Int,
//    val exp: Int,
//    val createDate: String,
//) {
//
//
//}