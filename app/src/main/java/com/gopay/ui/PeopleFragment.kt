package com.gopay.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gopay.R
import com.gopay.databinding.FragmentPeopleBinding
import com.gopay.ui.paging.PeopleAdapter
import com.gopay.ui.paging.PeopleLoadStateAdapter
import com.gopay.viewmodel.PeopleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [PeopleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class PeopleFragment : BaseFragment() {

    lateinit var binding: FragmentPeopleBinding
    override val layout: Int
        get() = R.layout.fragment_people

    val viewModel by viewModels<PeopleViewModel>()

    @Inject
    lateinit var pagingadater: PeopleAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPeopleBinding.bind(view)
        viewModel.getPeoples("")
        setUpPeopleData()


    }

    private fun setUpPeopleData() {
        with(binding) {
            with(pagingadater) {
                peoplerecyclerview.apply {
                    layoutManager =
                        LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                    addItemDecoration(
                        DividerItemDecoration(
                            requireContext(),
                            DividerItemDecoration.VERTICAL
                        )
                    )
                }
                peoplerecyclerview.adapter=withLoadStateHeaderAndFooter(
                    header = PeopleLoadStateAdapter(),
                    footer = PeopleLoadStateAdapter()
                )

                with(viewModel){
                    viewLifecycleOwner.lifecycleScope.launch {
                        peopleflow.collectLatest { submitData(it) }
                    }
                }
            }
        }
    }


}