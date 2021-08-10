package za.co.kaylentravispillay.api_v2.controller

import za.co.kaylentravispillay.api_v2.controller.util.ControllerResponse
import za.co.kaylentravispillay.api_v2.data.model.DataModelAboutSection

interface ControllerAbout {

    fun getAboutGetResponse(usedId: String?): ControllerResponse

    fun getAboutPostResponse(userId: String?, model: DataModelAboutSection?): ControllerResponse

    fun getAboutPutResponse(userId: String?, model: DataModelAboutSection?): ControllerResponse

    fun getAboutDeleteResponse(userId: String?, model: DataModelAboutSection?): ControllerResponse
}