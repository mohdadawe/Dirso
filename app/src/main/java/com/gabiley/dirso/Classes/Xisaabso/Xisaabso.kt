package com.gabiley.xisaabso.Classes.Xisaabso


data class Xisaabso(
    val current_page: Int,
    val `data`: List<XisaabsoData>,
    val first_page_url: String,
    val from: Int,
    val last_page: Int,
    val last_page_url: String,
    val links: List<LinkX>,
    val next_page_url: String,
    val path: String,
    val per_page: Int,
    val prev_page_url: Any,
    val to: Int,
    val total: Int
)