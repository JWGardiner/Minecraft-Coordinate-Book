package com.june.notebook.mixin;

import com.june.notebook.screens.bookSelectScreen;
import com.june.notebook.screens.menuScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.june.notebook.Notebook.BOOK_ICON;

@Environment(EnvType.CLIENT)
@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen implements BookScreen.Contents {

	int l = this.height / 4 + 48;

	protected TitleScreenMixin(Text title) {
		super(title);
	}
	@Inject(at = @At("RETURN"), method="initWidgetsNormal")
	private void addCustomButton(int y, int spacingY, CallbackInfo ci) {
		this.addDrawableChild(new TexturedButtonWidget(this.width / 2 + 104, y + spacingY, 20, 20, 0, 0, 20, BOOK_ICON, 32, 64, (button) -> {
			//Code is run when the button is clicked
			assert this.client != null;
			this.client.setScreen(new bookSelectScreen());
		}, Text.translatable("jwg.button.bookmenu")));
	}
}
