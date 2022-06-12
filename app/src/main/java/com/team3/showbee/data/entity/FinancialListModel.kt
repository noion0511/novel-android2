package com.team3.showbee.data.entity

//data class FinancialListModel(var title:String, var innerList: MutableList<FinancialContentModel>)
data class FinancialListModel(var list : MutableMap<String, MutableList<FinancialContentModel>>)
