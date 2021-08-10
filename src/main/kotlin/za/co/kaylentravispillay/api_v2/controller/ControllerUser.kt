package za.co.kaylentravispillay.api_v2.controller

import za.co.kaylentravispillay.api_v2.controller.util.ControllerResponse
import za.co.kaylentravispillay.api_v2.data.model.DataModelUser

interface ControllerUser {
    fun getUserGetResponse(id: String?): ControllerResponse

    fun getUserPostResponse(model: DataModelUser?): ControllerResponse

    fun getUserPutResponse(id: String?, model: DataModelUser?): ControllerResponse

    fun getUserDeleteResponse(id: String?): ControllerResponse
}