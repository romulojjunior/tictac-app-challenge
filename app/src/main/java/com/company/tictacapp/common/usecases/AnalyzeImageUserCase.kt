package com.company.tictacapp.common.usecases

import com.company.tictacapp.common.models.BitmapImage
import com.company.tictacapp.common.models.TicTocMapping


class AnalyzeImageUserCase : UseCaseBase<BitmapImage, TicTocMapping>() {
    override fun execute(param: BitmapImage): TicTocMapping {
        param.debugColor()
        return TicTocMapping()
    }
}