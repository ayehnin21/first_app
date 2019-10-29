package coder.seventy.two.fapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.GridLayoutManager
import coder.seventy.two.fapp.adapters.ProductAdapter
import coder.seventy.two.fapp.helper.H
import coder.seventy.two.fapp.models.Product
import coder.seventy.two.fapp.services.ServiceBuilder
import coder.seventy.two.fapp.services.WebService
import kotlinx.android.synthetic.main.activity_products.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Products : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        productRecycler.layoutManager = GridLayoutManager(this, 2)

        val service: WebService = ServiceBuilder.buildService(WebService::class.java)

        val id = 2
        val productService: Call<List<Product>> = service.getProducts("Bearer ${H.token}", id)

        productService.enqueue(object : Callback<List<Product>> {
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                toast(t.message!!)
            }

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {

                if (response.isSuccessful) {
                    val products: List<Product> = response.body()!!

                    productRecycler.adapter = ProductAdapter(this@Products, products)
                } else {
                    toast("Server Data Error!")
                }

            }

        })
    }

    fun addToCart(id: Int) {
        toast("Item Id $id is added to Cart!")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val menuItem = menu!!.findItem(R.id.addToCartMenu)
        MenuItemCompat.setActionView(menuItem, R.layout.my_out)
        return super.onCreateOptionsMenu(menu)
    }
}
