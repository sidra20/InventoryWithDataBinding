package com.sidrakotlin.inventoryroom

import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AppViewModel(app: Application): AndroidViewModel(app) {

    var proName = MutableLiveData<String>()
    var price = MutableLiveData<String>()
    var qty = MutableLiveData<String>()
    val allProducts: LiveData<List<Product>>

    private var isUpdate = false
    private lateinit var productUpdate: Product

    init{

        val dao = AppDatabase.getInstance(getApplication()).productDao()
        allProducts = dao.getAllProduct()
    }


    fun getProducts()
    {
        val dao = AppDatabase.getInstance(getApplication()).productDao()

        dao.getAllProduct()



    }

    fun insert(product: Product)
    {
        val dao = AppDatabase.getInstance(getApplication()).productDao()
        dao.insertProduct(product)
        getProducts()

    }

    fun update(product: Product)
    {
        val dao = AppDatabase.getInstance(getApplication()).productDao()
        dao.updateProduct(product)
        getProducts()
    }

    fun delete(product: Product)
    {
        val dao = AppDatabase.getInstance(getApplication()).productDao()
        dao.deleteProduct(product)
        getProducts()

    }

    fun addData()
    {


       if(proName.value!!.isNotEmpty() && price.value!!.isNotEmpty() && qty.value!!.isNotEmpty())
       {
            val pname = proName.value!!
           val ppice = price.value!!.toDouble()
           val pqty = qty.value!!.toInt()
            val obj = Product(0, pname, ppice, pqty)
            insert(obj)
            Toast.makeText(getApplication(), "Product added", Toast.LENGTH_SHORT).show()
        }


    }

    fun updateFileds(product: Product)
    {

        proName.value= product.name.toString()
        price.value = product.price!!.toDouble().toString()
        qty.value = product.qty!!.toInt().toString()
        isUpdate=true
        productUpdate = product

    }




}