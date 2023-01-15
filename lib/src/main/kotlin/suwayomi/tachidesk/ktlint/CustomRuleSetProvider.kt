package suwayomi.tachidesk.ktlint

/*
 * Copyright (C) Contributors to the Suwayomi project
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

import com.pinterest.ktlint.core.RuleProvider
import com.pinterest.ktlint.core.RuleSetProviderV2

internal const val CUSTOM_RULE_SET_ID = "tachidesk-rule-set"

public class CustomRuleSetProvider : RuleSetProviderV2(
    id = CUSTOM_RULE_SET_ID,
    about = NO_ABOUT
) {
    override fun getRuleProviders(): Set<RuleProvider> =
        setOf(
            RuleProvider { NoVarRule() },
        )
}