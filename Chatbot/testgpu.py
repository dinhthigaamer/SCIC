from openvino.runtime import Core

core = Core()
model = core.compile_model("model.xml", "GPU")  # Chạy trên GPU Intel
devices = core.available_devices
print("Available devices:", devices)
