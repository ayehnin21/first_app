package coder.seventy.two.fapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coder.seventy.two.fapp.Products
import coder.seventy.two.fapp.R
import coder.seventy.two.fapp.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_cart.view.*

class ProductAdapter(val context: Context, val products: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(context).inflate(R.layout.product_cart, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]

        holder.itemView.p_title.text = product.name
        holder.itemView.p_price.text = product.price.toString()
        Picasso.get().load(product.image).into(holder.itemView.p_image)

        holder.itemView.btnAddToCart.setOnClickListener {
            val productActivity = context as Products
            productActivity.addToCart(product.id)
        }

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}