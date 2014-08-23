require linux.inc

DESCRIPTION = "Linux kernel"
KERNEL_IMAGETYPE ?= "zImage"

COMPATIBLE_MACHINE = "(beaglebone)"

# The main PR is now using MACHINE_KERNEL_PR, for omap3 see conf/machine/include/omap3.inc
MACHINE_KERNEL_PR_append = "z"

FILESPATH =. "${FILE_DIRNAME}/linux-beagleboard-3.8:${FILE_DIRNAME}/linux-beagleboard-3.8/${MACHINE}:"

S = "${WORKDIR}/git"

PV = "3.8.13"

SRC_URI = "git://github.com/beagleboard/linux.git;branch=3.8"
SRCREV_pn-${PN} = "61a5e5a9d2362817f4445ce589a710df07d0c042"

do_configure_prepend() {
	if [ -e ${WORKDIR}/am335x-pm-firmware.bin ] ; then
		cp ${WORKDIR}/am335x-pm-firmware.bin ${S}/firmware
	fi
}

SRC_URI += " \
	file://btrfs/0001-decompressor-add-LZ4-decompressor-module.patch \
	file://btrfs/0002-lib-add-support-for-LZ4-compressed-kernel.patch \
	file://btrfs/0003-lib-add-lz4-compressor-module.patch \
	file://btrfs/0004-lib-lz4-correct-the-LZ4-license.patch \
	file://btrfs/0005-lz4-fix-compression-decompression-signedness-mismatc.patch \
	file://btrfs/0006-btrfs-prepare-incompat-flags-for-more-compression-me.patch \
	file://btrfs/0007-btrfs-lz4-add-wrapper-functions-and-enable-it.patch \
	file://btrfs/0008-btrfs-add-lz4hc-incompat-bits.patch \
	file://btrfs/0009-btrfs-add-lz4hc-wrapper-and-enable-it.patch \
	file://btrfs/0010-btrfs-reduce-duplicate-code-in-lz4_wrapper.c.patch \
	file://btrfs/0011-btrfs-select-LZ4-HC-libs.patch \
        file://0001-net-add-proper-db.txt.patch \
	file://defconfig \
	file://am335x-pm-firmware.bin \
	file://logo_linux_clut224.ppm \
"
