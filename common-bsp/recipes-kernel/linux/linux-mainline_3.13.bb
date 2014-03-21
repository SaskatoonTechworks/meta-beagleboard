require linux.inc

DESCRIPTION = "Linux kernel"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "(beaglebone)"

# The main PR is now using MACHINE_KERNEL_PR, for omap3 see conf/machine/include/omap3.inc
MACHINE_KERNEL_PR_append = "a"

FILESPATH =. "${FILE_DIRNAME}/linux-mainline-3.13:${FILE_DIRNAME}/linux-mainline-3.13/${MACHINE}:"

S = "${WORKDIR}/git"

PV = "3.13"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;branch=linux-3.13.y"
SRCREV_pn-${PN} = "404df65d0480f6da2b768f6c9b5259436b1de10f"

do_configure_prepend() {
	if [ -e ${WORKDIR}/am335x-pm-firmware.bin ] ; then
		cp ${WORKDIR}/am335x-pm-firmware.bin ${S}/firmware
	fi
}

SRC_URI += " \
	file://deassert-hard-reset/0001-ARM-omap-add-DT-support-for-deasserting-hardware-res.patch \
	file://dts/0001-arm-dts-am335x-boneblack-lcdc-add-panel-info.patch \
	file://dts/0002-arm-dts-am335x-boneblack-add-cpu0-opp-points.patch \
	file://dts/0003-arm-dts-am335x-bone-common-enable-and-use-i2c2.patch \
	file://dts/0004-arm-dts-am335x-bone-common-setup-default-pinmux-http.patch \
	file://fixes/0001-pinctrl-pinctrl-single-must-be-initialized-early.patch \
	file://pru/0001-Rebased-the-PRUSS-patch-from-3.12-commit-2c2a6c5.patch \
	file://sgx/0001-reset-Add-driver-for-gpio-controlled-reset-pins.patch \
	file://sgx/0002-prcm-port-from-ti-linux-3.12.y.patch \
	file://sgx/0003-ARM-DTS-AM335x-Add-SGX-DT-node.patch \
	file://sgx/0004-arm-Export-cache-flush-management-symbols-when-MULTI.patch \
	file://sgx/0005-hack-port-da8xx-changes-from-ti-3.12-repo.patch \
	file://sgx/0006-Revert-drm-remove-procfs-code-take-2.patch \
	file://sgx/0007-Changes-according-to-TI-for-SGX-support.patch \
	file://usb/0001-usb-musb-musb_host-Enable-ISOCH-IN-handling-for-AM33.patch \
	file://usb/0002-usb-musb-musb_cppi41-Make-CPPI-aware-of-high-bandwid.patch \
	file://usb/0003-usb-musb-musb_cppi41-Handle-ISOCH-differently-and-no.patch \
	file://static-capes/0001-dts-am335x-boneblack-default.patch \
	file://static-capes/0002-dts-boneblack-ttyO1-ttyO2-ttyO4.patch \
	file://static-capes/0003-Added-Argus-UPS-cape-support.patch \
	file://static-capes/0004-build-capes-one-layer.patch \
	file://defconfig \
  file://am335x-pm-firmware.bin \
"
