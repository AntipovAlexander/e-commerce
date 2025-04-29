package com.e_commerce.onboarding

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.e_commerce.core.theme.Theme
import com.e_commerce.core.ui.widgets.buttons.PrimaryButton
import com.e_commerce.core.ui.widgets.misc.HorizontalDivider
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun OnboardingScreen(onProceedToAuthClicked: () -> Unit) {
    val curveCutHeight = Theme.dimens.triplePad

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(-curveCutHeight),
    ) {
        val scope = rememberCoroutineScope()
        val imagePagerState = rememberPagerState(pageCount = { OnboardingConst.SLIDES_COUNT })
        val textPagerState = rememberPagerState(pageCount = { OnboardingConst.SLIDES_COUNT })
        val scrollingFollowingPair by remember {
            derivedStateOf {
                when {
                    imagePagerState.isScrollInProgress -> imagePagerState to textPagerState
                    textPagerState.isScrollInProgress -> textPagerState to imagePagerState
                    else -> null
                }
            }
        }
        // synchronizing two horizontal pager. Whe one is scrolled, the other follows it.
        LaunchedEffect(scrollingFollowingPair) {
            val (scrollingState, followingState) = scrollingFollowingPair ?: return@LaunchedEffect
            snapshotFlow { scrollingState.currentPage to scrollingState.currentPageOffsetFraction }
                .collect { update ->
                    val page = update.first
                    val offset = update.second
                    followingState.scrollToPage(page, offset)
                }
        }


        TopContent(imagePagerState)
        BottomContent(
            pagerState = textPagerState,
            curveCutHeight = curveCutHeight,
            onSkipClicked = onProceedToAuthClicked,
            onContinueClicked = {
                when (textPagerState.settledPage < OnboardingConst.SLIDES_COUNT - 1) {
                    true -> scope.launch {
                        textPagerState.animateScrollToPage(textPagerState.settledPage + 1)
                    }

                    false -> onProceedToAuthClicked()
                }
            }
        )
    }
}

/**
 * Represents top content of screen: slider with images
 */
@Composable
private fun ColumnScope.TopContent(pagerState: PagerState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = requireNotNull(OnboardingConst.slideImages[page])),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
        }
    }
}

/**
 * Represents bottom content of screen: slider with text, and animated buttons
 */
@Composable
private fun BottomContent(
    pagerState: PagerState,
    curveCutHeight: Dp,
    onSkipClicked: () -> Unit,
    onContinueClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.35f)
            .clip(topCircledShape(with(LocalDensity.current) { curveCutHeight.toPx() }))
            .background(color = Theme.colors.backgroundPrimary),
    ) {


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Spacer(modifier = Modifier.height(curveCutHeight))
            TextPager(
                pagerState = pagerState,
                modifier = Modifier.padding(Theme.dimens.doublePad)
            )
            HorizontalDivider()
            ButtonsContainer(pagerState, onSkipClicked, onContinueClicked)
            Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
        }
    }
}

@Composable
private fun ColumnScope.ButtonsContainer(
    pagerState: PagerState,
    onSkipClicked: () -> Unit,
    onContinueClicked: () -> Unit
) {
    var width by remember { mutableIntStateOf(0) }
    val isSkipVisible by remember {
        derivedStateOf { pagerState.currentPage < OnboardingConst.SLIDES_COUNT - 1 }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Theme.dimens.triplePad,
                vertical = Theme.dimens.doublePad
            )
            .onGloballyPositioned { width = it.size.width }
    ) {
        val space = Theme.dimens.triplePad
        // animate buttons width depending on skip button visibility
        val skipButtonWidth by animateIntAsState(
            targetValue = if (isSkipVisible) (width / 2 - space.value).roundToInt() else 0,
        )
        val continueButtonWidth by animateIntAsState(
            targetValue = if (isSkipVisible) (width / 2 - space.value).roundToInt() else width
        )
        PrimaryButton(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .width(with(LocalDensity.current) { skipButtonWidth.toDp() }),
            text = stringResource(R.string.skip),
            onClick = onSkipClicked
        )
        PrimaryButton(
            modifier = Modifier
                .width(with(LocalDensity.current) { continueButtonWidth.toDp() })
                .align(Alignment.CenterEnd),
            onClick = onContinueClicked,
            text = when (pagerState.currentPage < OnboardingConst.SLIDES_COUNT - 1) {
                true -> stringResource(R.string.next)
                false -> stringResource(R.string.got_it)
            }
        )
    }
}

@Composable
private fun ColumnScope.TextPager(pagerState: PagerState, modifier: Modifier = Modifier) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .then(modifier)
    ) { page ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(
                    requireNotNull(OnboardingConst.slideTitlesTextContent[page])
                ),
                color = Theme.colors.contentAccent,
                style = Theme.typography.heading.two.semiBold,
                modifier = Modifier.padding(horizontal = Theme.dimens.singlePad)
            )
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(
                    requireNotNull(OnboardingConst.slideDescriptionsTextContent[page])
                ),
                color = Theme.colors.contentSecondary,
                style = Theme.typography.body.three.regular,
                modifier = Modifier.padding(
                    top = Theme.dimens.doublePad,
                    start = Theme.dimens.doublePad,
                    end = Theme.dimens.doublePad
                )
            )
        }
    }
}

private fun topCircledShape(curveHeightPx: Float) = GenericShape { size, _ ->
    val width = size.width
    val height = size.height
    val path = Path().apply {
        moveTo(0f, 0f)
        quadraticTo(x1 = width / 2f, y1 = curveHeightPx * 2, x2 = width, y2 = 0f)
        lineTo(width, height)
        lineTo(0f, height)
        close()
    }
    addPath(path)
}

@Composable
@Preview
private fun OnboardingScreenPreview(
) {
    OnboardingScreen(onProceedToAuthClicked = {})
}