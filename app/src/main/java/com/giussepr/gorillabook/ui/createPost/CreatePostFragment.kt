package com.giussepr.gorillabook.ui.createPost

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.giussepr.gorillabook.R
import com.giussepr.gorillabook.databinding.CreatePostFragmentBinding
import com.giussepr.gorillabook.entity.feed.Feed
import com.giussepr.gorillabook.utility.viewModel.shared.FeedShareViewModel
import kotlin.random.Random


class CreatePostFragment : Fragment() {

    companion object {
        const val RC_ADD_PHOTO = 1
        fun newInstance() = CreatePostFragment()
    }

    private val viewModel: CreatePostViewModel by viewModels()
    private val shareViewModel: FeedShareViewModel by activityViewModels()

    private lateinit var binding: CreatePostFragmentBinding

    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        binding = DataBindingUtil.inflate(inflater, R.layout.create_post_fragment, container, false)

        binding.buttonAddPhoto.setOnClickListener {
            val pickPhoto = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(pickPhoto, RC_ADD_PHOTO)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.create_post_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.share_post -> {
                if (binding.textInputEditTextPostBody.text.toString().isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "You can't create empty post",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val feed = Feed(
                        id = Random.nextInt(999),
                        firstName = "John",
                        lastName = "Smith",
                        imageUrl = "",
                        imageUri = imageUri,
                        postBody = binding.textInputEditTextPostBody.text.toString(),
                        unixTimeStamp = (System.currentTimeMillis() / 1000).toString()
                    )
                    shareViewModel.addPost(feed)
                    shareViewModel.resetNewPost()
                    val keyboard: InputMethodManager =
                        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    keyboard.hideSoftInputFromWindow(
                        binding.textInputEditTextPostBody.windowToken,
                        0
                    )
                    findNavController().popBackStack()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_CANCELED) {
            when (requestCode) {
                RC_ADD_PHOTO -> if (resultCode == RESULT_OK) {
                    val selectedImage = data?.data
                    imageUri = data?.data
                    val filePathColumn =
                        arrayOf(MediaStore.Images.Media.DATA)
                    if (selectedImage != null) {
                        val cursor = requireContext().contentResolver.query(
                            selectedImage,
                            filePathColumn, null, null, null
                        )
                        if (cursor != null) {
                            cursor.moveToFirst()
                            val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
                            val picturePath: String = cursor.getString(columnIndex)
                            Log.i("CreatePost", picturePath)
                            //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath))
                            cursor.close()
                        }
                    }
                }
            }
        }
    }

}