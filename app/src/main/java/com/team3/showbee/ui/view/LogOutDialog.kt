package com.team3.showbee.ui.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.team3.showbee.R
import com.team3.showbee.databinding.DialogLogOutBinding
import com.team3.showbee.ui.viewmodel.LogInViewModel

class LogOutDialog(): DialogFragment() {
    private var _binding: DialogLogOutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogLogOutBinding.inflate(inflater, container, false)
        val view = binding.root

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnLogoutOk.setOnClickListener {
            buttonClickListener.onLogOutOkClicked()
            dismiss()
        }
        binding.btnLogoutCancel.setOnClickListener {
//            buttonClickListener.onLogOutCancelClicked()
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnButtonClickListener {
        fun onLogOutOkClicked()
//        fun onLogOutCancelClicked()
    }

    fun setButtonClickListener(buttonClickListener: OnButtonClickListener) {
        this.buttonClickListener = buttonClickListener
    }

    private lateinit var buttonClickListener: OnButtonClickListener
}