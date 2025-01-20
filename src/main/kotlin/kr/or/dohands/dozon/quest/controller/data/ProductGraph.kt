package kr.or.dohands.dozon.quest.controller.data

import java.math.BigDecimal

data class ProductGraph(
    val week : Long?,
    val value : BigDecimal
) {
    data class List(
        val data : MutableList<ProductGraph>
    ){
        companion object{
            fun from(data: MutableList<ProductGraph>):List{
                return ProductGraph.List(
                    data
                )
            }
        }
    }

    companion object{
        fun of(week: Long?, value : BigDecimal): ProductGraph{
            return ProductGraph(
                week, value
            )
        }
    }
}